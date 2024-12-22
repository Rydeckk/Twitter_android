import { Controller, Post, Body, Request } from '@nestjs/common';
import { CommentsService } from './comments.service';
import { CreateCommentDto } from './dto/comment.dto';

@Controller('comments')
export class CommentsController {
  constructor(private readonly commentsService: CommentsService) {}

  @Post()
  createTweetComment(@Request() req: any, @Body() body: CreateCommentDto) {
    return this.commentsService.createTweetComment(req.user.sub, body);
  }
}
