import { Controller, Post, Body, Delete, Request, Get } from '@nestjs/common';
import { ConversationsService } from './conversations.service';
import {
  CreateConversationDto,
  DeleteConversationDto,
} from './dto/conversation.dto';

@Controller('conversations')
export class ConversationsController {
  constructor(private readonly conversationsService: ConversationsService) {}

  @Post('user')
  createConversation(@Request() req: any, @Body() body: CreateConversationDto) {
    return this.conversationsService.createConversation(req.user.sub, body);
  }

  @Get('user')
  getUserConversations(@Request() req: any) {
    return this.conversationsService.getUserConversations(req.user.sub);
  }

  @Delete('user')
  deleteUserConversation(
    @Request() req: any,
    @Body() body: DeleteConversationDto,
  ) {
    return this.conversationsService.deleteUserConversation(req.user.sub, body);
  }
}
