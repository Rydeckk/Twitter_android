import { Injectable } from '@nestjs/common';
import { AuthRegisterDto } from 'src/auth/dto/auth.dto';
import { PrismaService } from 'src/prisma.service';
import { UpdateUserDto } from './dto/users.dto';

@Injectable()
export class UsersService {
  constructor(private readonly prisma: PrismaService) {}

  async findUserById(userId: string) {
    return this.prisma.users.findUnique({
      where: {
        id: userId,
      },
    });
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

  updateCurrentUser(currentUserId: string, data: UpdateUserDto) {
    return this.prisma.users.update({
      where: {
        id: currentUserId,
      },
      data,
    });
  }
}
