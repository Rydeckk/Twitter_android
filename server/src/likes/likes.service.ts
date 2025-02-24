import { Injectable } from '@nestjs/common';
import { PrismaService } from 'src/prisma.service';
import { CreateLikeDto } from './dto/like.dto';

@Injectable()
export class LikesService {
  constructor(private prisma: PrismaService) {}

  async likeTweet({ userId, tweetId }: CreateLikeDto) {
    return this.prisma.likes.create({
      data: {
        tweet: {
          connect: {
            id: tweetId,
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
