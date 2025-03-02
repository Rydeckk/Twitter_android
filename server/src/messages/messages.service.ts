import { Injectable } from '@nestjs/common';
import { CreateMessageDto } from './dto/message.dto';
import { PrismaService } from 'src/prisma.service';
import { MessagesGateway } from './gateway/message-gateway';

@Injectable()
export class MessagesService {
  constructor(private readonly prisma: PrismaService, private readonly messagesGateway: MessagesGateway) {}

  async sendMessage(currentUserId: string, data: CreateMessageDto) {
    const message = await this.prisma.messages.create({
      data: {
        message: data.message,
        conversationId: data.conversationId,
        userId: currentUserId,
      },
      include: {
        users: true
      }
    });

    const conversationUsers = await this.prisma.conversationsUsers.findMany({
      where: { conversationId: message.conversationId },
      select: { userId: true },
    });


    conversationUsers.forEach(user => {
        if (user.userId !== currentUserId) {
            this.messagesGateway.sendMessageToUser(user.userId, message);
        }
    });

    return message;
  }

  getConversationMessages(conversationId: string) {
    return this.prisma.messages.findMany({
      where: {
        conversationId: conversationId,
      },
      orderBy: {
        createdAt: 'asc',
      },
      include: {
        users: true,
      },
    });
  }
}
