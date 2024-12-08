import {
  Controller,
  Get,
  Post,
  Body,
  Param,
  ParseUUIDPipe,
  Request,
} from '@nestjs/common';
import { MessagesService } from './messages.service';
import { CreateMessageDto } from './dto/message.dto';

@Controller('messages')
export class MessagesController {
  constructor(private readonly messagesService: MessagesService) {}

  @Post()
  sendMessage(@Request() req: any, @Body() body: CreateMessageDto) {
    return this.messagesService.sendMessage(req.user.sub, body);
  }

  @Get(':conversationId')
  findAll(@Param('conversationId', ParseUUIDPipe) conversationId: string) {
    return this.messagesService.getConversationMessages(conversationId);
  }
}
