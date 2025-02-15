import { Injectable } from '@nestjs/common';
import { CreateTweetDto, UpdateTweetDto } from './dto/tweet.dto';
import { PrismaService } from 'src/prisma.service';
import { Prisma } from '@prisma/client';
import { FollowsService } from 'src/follows/follows.service';

const include: Prisma.TweetsInclude = {
  users: true,
};

const orderBy:
  | Prisma.TweetsOrderByWithRelationInput
  | Prisma.TweetsOrderByWithRelationInput[] = {
  createdAt: 'desc',
};

@Injectable()
export class TweetsService {
  constructor(
    private prisma: PrismaService,
    private followsService: FollowsService,
  ) {}

  async create(data: CreateTweetDto) {
    return this.prisma.tweets.create({
      data,
    });
  }

  async getAllTweets() {
    return this.prisma.tweets.findMany({
      include,
      orderBy,
    });
  }

  async getAllFollowTweets(userId: string) {
    const userIds = (await this.followsService.getUserFollowings(userId)).map(
      ({ followingId }) => followingId,
    );
    return this.prisma.tweets.findMany({
      where: {
        userId: { in: userIds },
      },
      include,
      orderBy,
    });
  }

  async getAllUserTweets(id: string) {
    return this.prisma.tweets.findMany({
      where: {
        userId: id,
      },
      include,
      orderBy,
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
