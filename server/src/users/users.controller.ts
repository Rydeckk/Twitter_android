import { Body, Controller, Get, Param, Put, Request } from '@nestjs/common';
import { UsersService } from './users.service';
import { UpdateUserDto } from './dto/users.dto';

@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  getCurrentUser(@Request() req: Request) {
    return this.usersService.findUserById(req.user.sub);
  }

  @Get(':userId')
  getUserById(@Param('userId') userId: string) {
    return this.usersService.findUserById(userId);
  }

  @Put(':userId')
  async updateCurrentUser(
    @Param('userId') userId: string,
    @Body() body: UpdateUserDto,
  ) {
    return this.usersService.updateCurrentUser(userId, body);
  }
}
