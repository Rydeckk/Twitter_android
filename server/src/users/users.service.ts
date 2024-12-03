import { Injectable } from '@nestjs/common';
import { AuthRegisterDto } from 'src/auth/dto/auth.dto';
import { PrismaService } from 'src/prisma.service';

@Injectable()
export class UsersService {
  constructor(private readonly prisma: PrismaService) {}

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
}
