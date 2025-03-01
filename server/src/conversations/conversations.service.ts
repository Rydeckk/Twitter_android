import { Injectable } from '@nestjs/common';
import {
  CreateConversationDto,
  DeleteConversationDto,
} from './dto/conversation.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class ConversationsService {
  constructor(private readonly prisma: PrismaService) {}

  async createConversation(currentUserId: string, data: CreateConversationDto) {
    const findExistingConv = await this.prisma.conversations.findFirst({
      where: {
        conversationsUsers: {
          every: {
            userId: {
              in: [currentUserId, ...data.userIds].map((userId) => userId),
            },
          },
        },
      },
    });

    if (findExistingConv) {
      return null;
    }

    const conversationCreated = await this.prisma.conversations.create({
      data: {
        createdByUser: {
          connect: {
            id: currentUserId,
          },
        },
        conversationsUsers: {
          createMany: {
            data: [currentUserId, ...data.userIds].map((userId) => ({
              userId,
            })),
          },
        },
      },
      include: {
        conversationsUsers: {
          include: {
            users: true,
          },
        },
      },
    });

    return {
      ...conversationCreated,
      conversationsUsers: conversationCreated.conversationsUsers.filter(
        ({ userId }) => userId !== currentUserId,
      ),
    };
  }

  async getUserConversations(currentUserId: string) {
    const conversations = await this.prisma.conversations.findMany({
      where: {
        conversationsUsers: {
          some: {
            userId: currentUserId,
          },
        },
      },
      include: {
        conversationsUsers: {
          include: {
            users: true,
          },
        },
      },
    });

    return conversations.map((data) => {
      return {
        ...data,
        conversationsUsers: data.conversationsUsers.filter(
          ({ userId }) => userId !== currentUserId,
        ),
      };
    });
  }

  deleteUserConversation(data: DeleteConversationDto) {
    return this.prisma.conversations.delete({
      where: {
        id: data.conversationUserId,
      },
    });
  }
}
