import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
  ParseUUIDPipe,
  Request,
} from '@nestjs/common';
import { TweetsService } from './tweets.service';
import { CreateTweetDto, LikeDto, UpdateTweetDto } from './dto/tweet.dto';

@Controller('tweets')
export class TweetsController {
  constructor(private readonly tweetsService: TweetsService) {}

  @Post()
  create(@Request() req: Request, @Body() body: CreateTweetDto) {
    return this.tweetsService.create({ ...body, userId: req.user.sub });
  }

  @Post('like')
  likeTweet(@Request() req: Request, @Body() body: LikeDto) {
    return this.tweetsService.likeTweet({
      ...body,
      userId: req.user.sub,
    });
  }

  @Get()
  findAllTweets() {
    return this.tweetsService.getAllTweets();
  }

  @Get('follow')
  findFollowTweets(@Request() req: Request) {
    return this.tweetsService.getAllFollowTweets(req.user.sub);
  }

  @Get('user/:userId')
  findAllUserTweets(@Param('userId', ParseUUIDPipe) userId: string) {
    return this.tweetsService.getAllUserTweets(userId);
  }

  @Get('like/:userId')
  findLikesTweets(@Param('userId', ParseUUIDPipe) userId: string) {
    return this.tweetsService.getLikesTweets(userId);
  }

  @Get(':id')
  findOne(@Param('id', ParseUUIDPipe) id: string) {
    return this.tweetsService.findOne(id);
  }

  @Patch(':id')
  update(@Param('id', ParseUUIDPipe) id: string, @Body() body: UpdateTweetDto) {
    return this.tweetsService.update(id, body);
  }

  @Delete(':id')
  remove(@Param('id', ParseUUIDPipe) id: string) {
    return this.tweetsService.remove(id);
  }
}
