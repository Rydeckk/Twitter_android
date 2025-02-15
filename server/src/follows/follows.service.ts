import { Injectable } from '@nestjs/common';
import { PrismaService } from 'src/prisma.service';
import { CreateFollowDto } from './dto/follow.dto';

@Injectable()
export class FollowsService {
  constructor(private prisma: PrismaService) {}

  async follow(data: CreateFollowDto) {
    return this.prisma.follows.create({
      data,
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
