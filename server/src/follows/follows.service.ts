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
    const followers = await this.prisma.follows.findMany({
      where: {
        followingId: id,
      },
      include: {
        followedBy: true,
      },
    });

    const followings = await this.getUserFollowings(id);

    return followers.map((data) => ({
      ...data,
      isUserAlsoFollowing: !!followings.find(
        (test) => test.followingId === data.followedById,
      ),
    }));
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
