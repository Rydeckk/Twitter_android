import { Body, Controller, Get, Put, Request } from '@nestjs/common';
import { UsersService } from './users.service';
import { UpdateUserDto } from './dto/users.dto';

@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  getCurrentUser(@Request() req: Request) {
    return this.usersService.findUserById(req.user.sub);
  }

  @Put()
  updateCurrentUser(@Request() req: Request, @Body() body: UpdateUserDto) {
    return this.usersService.updateCurrentUser(req.user.sub, body);
  }
}
