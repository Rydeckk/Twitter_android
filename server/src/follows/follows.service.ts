import { Injectable } from '@nestjs/common';
import { CreateFollowDto } from './dto/follow.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class FollowsService {
  constructor(private readonly prisma: PrismaService) {}

  follow(userId: string, data: CreateFollowDto) {
    const { followingId } = data;
    return this.prisma.follows.create({
      data: {
        followedById: userId,
        followingId,
      },
    });
  }

  unfollow(userId: string, followingId: string) {
    return this.prisma.follows.delete({
      where: {
        followingId_followedById: {
          followedById: userId,
          followingId,
        },
      },
    });
  }
}
