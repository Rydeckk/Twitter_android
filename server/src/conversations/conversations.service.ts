import { Injectable } from '@nestjs/common';
import {
  CreateConversationDto,
  DeleteConversationDto,
} from './dto/conversation.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class ConversationsService {
  constructor(private readonly prisma: PrismaService) {}

  createConversation(currentUserId: string, data: CreateConversationDto) {
    return this.prisma.conversations.create({
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
    });
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

  deleteUserConversation(currentUserId: string, data: DeleteConversationDto) {
    return this.prisma.conversationsUsers.delete({
      where: {
        id: data.conversationUserId,
        userId: currentUserId,
      },
    });
  }
}
