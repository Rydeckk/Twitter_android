import { Injectable } from '@nestjs/common';

import { PrismaService } from 'src/prisma.service';
import { CreateRetweetDto } from './dto/retweet.dto';

@Injectable()
export class RetweetsService {
  constructor(private prisma: PrismaService) {}

  async createRetweet({
    userId,
    content,
    type,
    parentTweetId,
  }: CreateRetweetDto) {
    return this.prisma.retweets.create({
      data: {
        type,
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
