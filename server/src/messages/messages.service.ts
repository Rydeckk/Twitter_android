import { Injectable } from '@nestjs/common';
import { CreateMessageDto } from './dto/message.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class MessagesService {
  constructor(private readonly prisma: PrismaService) {}

  sendMessage(currentUserId: string, data: CreateMessageDto) {
    return this.prisma.messages.create({
      data: {
        message: data.message,
        conversationId: data.conversationId,
        userId: currentUserId,
      },
    });
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
