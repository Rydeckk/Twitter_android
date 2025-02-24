import { Injectable } from '@nestjs/common';
import { CreateCommentDto } from './dto/comment.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class CommentsService {
  constructor(private prisma: PrismaService) {}

  async create({ content, parentTweetId, userId }: CreateCommentDto) {
    return this.prisma.comments.create({
      data: {
        tweet: {
          create: {
            content,
            users: {
              connect: {
                id: userId,
              },
            },
          },
        },
        parentTweet: {
          connect: {
            id: parentTweetId,
          },
        },
        users: {
          connect: {
            id: userId,
          },
        },
      },
    });
  }
}
