import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Request,
} from '@nestjs/common';
import { FollowsService } from './follows.service';
import { CreateFollowDto, UnfollowDto } from './dto/follow.dto';

@Controller('follows')
export class FollowsController {
  constructor(private readonly followsService: FollowsService) {}

  @Post()
  follow(@Request() req: Request, @Body() body: CreateFollowDto) {
    return this.followsService.follow({
      ...body,
      followedById: req.user.sub,
    });
  }

  @Delete('')
  unfollow(@Request() req: Request, @Body() body: UnfollowDto) {
    return this.followsService.unfollow({
      ...body,
      followedById: req.user.sub,
    });
  }

  @Get('followers/:userId')
  findUserFollowers(@Param('userId') userId: string) {
    return this.followsService.getUserFollowers(userId);
  }

  @Get('followings/:userId')
  findUserFollowings(@Param('userId') userId: string) {
    return this.followsService.getUserFollowings(userId);
  }
}
