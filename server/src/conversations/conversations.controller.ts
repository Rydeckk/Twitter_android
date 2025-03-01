import { Controller, Post, Body, Request, Get, Put } from '@nestjs/common';
import { ConversationsService } from './conversations.service';
import {
  CreateConversationDto,
  DeleteConversationDto,
} from './dto/conversation.dto';

@Controller('conversations')
export class ConversationsController {
  constructor(private readonly conversationsService: ConversationsService) {}

  @Post('user')
  createConversation(
    @Request() req: Request,
    @Body() body: CreateConversationDto,
  ) {
    return this.conversationsService.createConversation(req.user.sub, body);
  }

  @Get('user')
  getUserConversations(@Request() req: Request) {
    return this.conversationsService.getUserConversations(req.user.sub);
  }

  @Put('user')
  deleteUserConversation(@Body() body: DeleteConversationDto) {
    return this.conversationsService.deleteUserConversation(body);
  }
}
