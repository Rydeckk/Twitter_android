import { Body, Controller, Post, Request } from '@nestjs/common';
import { RetweetsService } from './retweets.service';
import { CreateRetweetDto } from './dto/retweet.dto';

@Controller('retweets')
export class RetweetsController {
  constructor(private readonly retweetsService: RetweetsService) {}

  @Post('')
  createRetweet(@Request() req: Request, @Body() body: CreateRetweetDto) {
    return this.retweetsService.createRetweet({
      ...body,
      userId: req.user.sub,
      content: body.type === 'REPOST' ? '' : body.content,
    });
  }
}
