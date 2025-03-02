import { Module } from '@nestjs/common';
import { MessagesService } from './messages.service';
import { MessagesController } from './messages.controller';
import { PrismaService } from 'src/prisma.service';
import { MessagesGateway } from './gateway/message-gateway';

@Module({
  controllers: [MessagesController],
  providers: [MessagesService, PrismaService, MessagesGateway],
  exports: [MessagesGateway]
})
export class MessagesModule {}
