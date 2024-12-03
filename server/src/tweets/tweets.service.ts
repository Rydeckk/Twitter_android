import { Injectable } from '@nestjs/common';
import { CreateTweetDto, UpdateTweetDto } from './dto/tweet.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class TweetsService {
  constructor(private prisma: PrismaService) {}

  async create(data: CreateTweetDto) {
    return this.prisma.tweets.create({
      data,
    });
  }

  async getAllUserTweets(id: string) {
    return this.prisma.tweets.findMany({
      where: {
        userId: id,
      },
    });
  }

  async findOne(id: string) {
    return this.prisma.tweets.findUnique({
      where: {
        id,
      },
    });
  }

  async update(id: string, data: UpdateTweetDto) {
    return this.prisma.tweets.update({
      where: {
        id,
      },
      data,
    });
  }

  async remove(id: string) {
    return this.prisma.tweets.delete({
      where: {
        id,
      },
    });
  }
}
