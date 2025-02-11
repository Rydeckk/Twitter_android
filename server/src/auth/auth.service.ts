import { Injectable, UnauthorizedException } from '@nestjs/common';
import { UsersService } from 'src/users/users.service';
import { JwtService } from '@nestjs/jwt';
import { AuthRegisterDto } from './dto/auth.dto';
import { hash, compare } from 'bcrypt';

@Injectable()
export class AuthService {
  constructor(
    private readonly usersService: UsersService,
    private readonly jwtService: JwtService,
  ) {}

  async register(data: AuthRegisterDto) {
    const { password, ...rest } = data;
    const passwordHashed = await hash(password, 10);

    return this.usersService.createUser({
      ...rest,
      password: passwordHashed,
    });
  }

  async login(email: string, password: string) {
    const user = await this.usersService.findUserByEmail(email);
    const isPasswordValid = await compare(password, user.password);

    if (!user || !isPasswordValid) {
      throw new UnauthorizedException();
    }
    return {
      access_token: await this.jwtService.signAsync({
        sub: user.id,
        email: user.email,
      }),
    };
  }
}
