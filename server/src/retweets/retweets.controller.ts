import { Controller, Post, Body, Request } from '@nestjs/common';
import { RetweetsService } from './retweets.service';
import { CreateRetweetDto } from './dto/retweet.dto';

@Controller('retweets')
export class RetweetsController {
  constructor(private readonly retweetsService: RetweetsService) {}

  @Post()
  createTweetRetweet(@Request() req: any, @Body() body: CreateRetweetDto) {
    return this.retweetsService.createTweetRetweet(req.user.sub, body);
  }
}
