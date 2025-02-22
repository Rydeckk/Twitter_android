import { Injectable } from '@nestjs/common';
import { AuthRegisterDto } from 'src/auth/dto/auth.dto';
import { PrismaService } from 'src/prisma.service';
import { UpdateUserDto } from './dto/users.dto';
import { Prisma } from '@prisma/client';

const include: Prisma.UsersInclude = {
  _count: {
    select: {
      followedBy: true,
      following: true,
    },
  },
};

@Injectable()
export class UsersService {
  constructor(private readonly prisma: PrismaService) {}

  async findUserById(userId: string) {
    const user = await this.prisma.users.findUnique({
      where: {
        id: userId,
      },
      include,
    });
    return {
      ...user,
      followingCount: user?._count?.following,
      followedByCount: user?._count?.followedBy,
    };
  }

  async findUserByEmail(email: string) {
    return this.prisma.users.findUnique({
      where: {
        email,
      },
    });
  }

  async createUser(data: AuthRegisterDto) {
    return this.prisma.users.create({
      data,
    });
  }

  async updateCurrentUser(currentUserId: string, data: UpdateUserDto) {
    return this.prisma.users.update({
      where: {
        id: currentUserId,
      },
      data,
    });
  }
}
