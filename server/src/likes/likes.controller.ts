import { Body, Controller, Delete, Post, Request } from '@nestjs/common';
import { LikesService } from './likes.service';
import { CreateLikeDto, DeleteTweetLikeDto } from './dto/like.dto';

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

  @Delete('')
  unlikeTweet(@Request() req: Request, @Body() body: DeleteTweetLikeDto) {
    return this.likesService.unlikeTweet({
      ...body,
      userId: req.user.sub,
    });
  }
}
