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

  async getUserFollowers(currentUserId: string, id: string) {
    const followers = await this.prisma.follows.findMany({
      where: {
        followingId: id,
      },
      include: {
        followedBy: true,
      },
    });

    const followings = await this.prisma.follows.findMany({
      where: {
        followedById: currentUserId,
      },
      include: {
        following: true,
      },
    });

    return followers.map((data) => ({
      ...data,
      isUserAlsoFollowing: !!followings.find(
        ({ followingId, followedById }) =>
          followingId === data.followedById && followedById === currentUserId,
      ),
    }));
  }

  async getUserFollowings(currentUserId: string, id: string) {
    const followings = await this.prisma.follows.findMany({
      where: {
        followedById: id,
      },
      include: {
        following: true,
      },
    });

    if (currentUserId === id) return followings;

    const currentUserFollowings = await this.prisma.follows.findMany({
      where: {
        followedById: currentUserId,
      },
      include: {
        following: true,
      },
    });

    return followings.map((data) => ({
      ...data,
      isUserAlsoFollowing: !!currentUserFollowings.find(
        ({ followingId, followedById }) =>
          data.followingId === followingId && followedById === currentUserId,
      ),
    }));
  }
}
