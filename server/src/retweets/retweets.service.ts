import { Injectable } from '@nestjs/common';
import { CreateRetweetDto } from './dto/retweet.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class RetweetsService {
  constructor(private readonly prisma: PrismaService) {}

  createTweetRetweet(userId: string, data: CreateRetweetDto) {
    const { content, type, parentTweetId } = data;
    return this.prisma.tweets.create({
      data: {
        userId,
        content,
        retweet: {
          create: {
            type,
            userId,
            parentTweetId,
          },
        },
      },
    });
  }
}
