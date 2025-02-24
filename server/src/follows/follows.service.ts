import { Injectable } from '@nestjs/common';
import { PrismaService } from 'src/prisma.service';
import { CreateFollowDto, UnfollowDto } from './dto/follow.dto';

@Injectable()
export class FollowsService {
  constructor(private prisma: PrismaService) {}

  async follow(data: CreateFollowDto) {
    return this.prisma.follows.create({
      data,
    });
  }

  async unfollow({ followedById, followingId }: UnfollowDto) {
    return this.prisma.follows.delete({
      where: {
        followingId_followedById: {
          followedById,
          followingId,
        },
      },
    });
  }

  async getUserFollowers(id: string) {
    return this.prisma.follows.findMany({
      where: {
        followingId: id,
      },
      include: {
        followedBy: true,
      },
    });
  }

  async getUserFollowings(id: string) {
    return this.prisma.follows.findMany({
      where: {
        followedById: id,
      },
      include: {
        following: true,
      },
    });
  }
}
