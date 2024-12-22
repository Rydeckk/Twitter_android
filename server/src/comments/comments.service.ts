import { Injectable } from '@nestjs/common';
import { CreateCommentDto } from './dto/comment.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class CommentsService {
  constructor(private readonly prisma: PrismaService) {}

  createTweetComment(userId: string, data: CreateCommentDto) {
    const { content, parentTweetId } = data;
    return this.prisma.tweets.create({
      data: {
        content,
        userId,
        comment: {
          create: {
            parentTweetId,
            userId,
          },
        },
      },
    });
  }
}
