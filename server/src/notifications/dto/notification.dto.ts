import { NOTIFICATION_TYPE } from '@prisma/client';
import { IsNotEmpty } from 'class-validator';

export class CreateNotificationDto {
  userId: string;

  @IsNotEmpty()
  eventId: string;

  @IsNotEmpty()
  eventType: NOTIFICATION_TYPE;
}
