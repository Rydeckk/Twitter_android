import { Controller, Post, Body, Request, Delete } from '@nestjs/common';
import { FollowsService } from './follows.service';
import { CreateFollowDto, DeleteFollowDto } from './dto/follow.dto';

@Controller('follows')
export class FollowsController {
  constructor(private readonly followsService: FollowsService) {}

  @Post()
  follow(@Request() req: any, @Body() body: CreateFollowDto) {
    return this.followsService.follow(req.user.sub, body);
  }

  @Delete()
  unfollow(@Request() req: any, @Body() body: DeleteFollowDto) {
    return this.followsService.unfollow(req.user.sub, body.followingId);
  }
}
