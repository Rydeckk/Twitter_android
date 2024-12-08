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

  getUserConversations(currentUserId: string) {
    return this.prisma.conversations.findMany({
      where: {
        conversationsUsers: {
          some: {
            userId: currentUserId,
          },
        },
      },
      include: {
        conversationsUsers: true,
      },
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
