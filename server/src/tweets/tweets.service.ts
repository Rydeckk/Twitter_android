import { Injectable } from '@nestjs/common';
import { CreateTweetDto, LikeDto, UpdateTweetDto } from './dto/tweet.dto';
import { PrismaService } from 'src/prisma.service';
import { Prisma } from '@prisma/client';
import { FollowsService } from 'src/follows/follows.service';

const include: Prisma.TweetsInclude = {
  users: true,
  like: true,
  tweetComments: true,
  tweetRetweets: true,
  pictures: true,
  retweet: true,
  comment: true,
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

  async likeTweet({ userId, tweetId }: LikeDto) {
    return this.prisma.likes.create({
      data: {
        userId,
        tweetId,
      },
    });
  }

  async getAllTweets() {
    return this.prisma.tweets.findMany({
      where: {
        AND: [
          {
            comment: { is: null },
          },
          {
            retweet: { is: null },
          },
        ],
      },
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

  async getAllUserTweets(userId: string) {
    return this.prisma.tweets.findMany({
      where: {
        userId,
      },
      include,
      orderBy,
    });
  }

  async getLikesTweets(userId: string) {
    return this.prisma.tweets.findMany({
      where: {
        like: {
          some: {
            userId,
          },
        },
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
