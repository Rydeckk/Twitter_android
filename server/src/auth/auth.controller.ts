import { Body, Controller, Post } from '@nestjs/common';
import { AuthService } from './auth.service';
import { AuthLoginDto, AuthRegisterDto } from './dto/auth.dto';
import { Public } from './auth.guard';

@Controller('auth')
export class AuthController {
  constructor(private authService: AuthService) {}

  @Public()
  @Post('register')
  async register(@Body() body: AuthRegisterDto) {
    try {
      await this.authService.register(body);
      return {
        status: 200,
      };
    } catch (error) {
      return {
        status: 500,
      };
    }
  }

  @Public()
  @Post('login')
  async login(@Body() body: AuthLoginDto) {
    const { email, password } = body;
    return this.authService.login(email, password);
  }
}
