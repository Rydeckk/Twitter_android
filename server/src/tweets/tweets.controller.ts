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
import { CreateTweetDto, UpdateTweetDto } from './dto/tweet.dto';

@Controller('tweets')
export class TweetsController {
  constructor(private readonly tweetsService: TweetsService) {}

  @Post()
  create(@Body() body: CreateTweetDto) {
    return this.tweetsService.create(body);
  }

  @Get('user')
  // TODO @nicolas
  findAllUserTweets(@Request() req: any) {
    return this.tweetsService.getAllUserTweets(req.user.sub);
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
