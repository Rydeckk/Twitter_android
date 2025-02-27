import { Controller, Request, Put } from '@nestjs/common';
import { NotificationsService } from './notifications.service';

@Controller('notifications')
export class NotificationsController {
  constructor(private readonly notificationsService: NotificationsService) {}

  @Put('read')
  updateUserNotifToReaded(@Request() request: Request) {
    return this.notificationsService.updateUserNotifToReaded(request.user.sub);
  }
}
