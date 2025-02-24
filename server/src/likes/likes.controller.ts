import { Body, Controller, Post, Request } from '@nestjs/common';
import { LikesService } from './likes.service';
import { CreateLikeDto } from './dto/like.dto';

@Controller('likes')
export class LikesController {
  constructor(private readonly likesService: LikesService) {}

  @Post('')
  likeTweet(@Request() req: Request, @Body() body: CreateLikeDto) {
    return this.likesService.likeTweet({
      ...body,
      userId: req.user.sub,
    });
  }
}
