import { Request as NestRequest } from '@nestjs/common';

declare module '@nestjs/common' {
  interface Request extends NestRequest {
    user?: { sub: string; email: string };
  }
}
