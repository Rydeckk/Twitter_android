import { Injectable } from '@nestjs/common';
import { CreateNotificationDto } from './dto/notification.dto';
import { PrismaService } from 'src/prisma.service';
// import { NOTIFICATION_TYPE, Users } from '@prisma/client';

@Injectable()
export class NotificationsService {
  constructor(private prisma: PrismaService) {}

  async getNotificationsTextByType({
    eventId,
    userId,
  }: {
    eventId: string;
    userId: string;
  }) {
    return {
      FOLLOW: await this.prisma.follows.findUnique({
        where: {
          followingId_followedById: {
            followingId: eventId,
            followedById: userId,
          },
        },
        include: {
          followedBy: true,
          following: true,
        },
      }),
      LIKE: await this.prisma.likes.findUnique({
        where: {
          id: eventId,
        },
        include: {
          tweet: true,
          users: true,
        },
      }),
      RETWEET: await this.prisma.retweets.findFirst({
        where: {
          id: eventId,
        },
        include: {
          users: true,
        },
      }),
      COMMENT: await this.prisma.comments.findFirst({
        where: {
          id: eventId,
        },
        include: {
          users: true,
        },
      }),
      MESSAGE: await this.prisma.messages.findFirst({
        where: {
          id: eventId,
        },
        include: {
          users: true,
        },
      }),
    };
  }

  async getUserNotification(userId: string) {
    const notifications = await this.prisma.notifications.findMany({
      where: {
        userId,
      },
    });

    // const notificationsText: {
    //   type: NOTIFICATION_TYPE;
    //   text: string;
    //   users: Users;
    // }[] = [];

    // for (const { eventId, eventType } of notifications) {
    //   const test = await this.getNotificationsTextByType({ eventId, userId });
    // }
    return notifications;
  }

  async create({ eventId, eventType, userId }: CreateNotificationDto) {
    return this.prisma.notifications.create({
      data: {
        eventId,
        eventType,
        isRead: false,
        users: {
          connect: {
            id: userId,
          },
        },
      },
    });
  }

  async updateUserNotifToReaded(userId: string) {
    return this.prisma.notifications.updateMany({
      where: {
        userId,
      },
      data: {
        isRead: true,
      },
    });
  }
}
