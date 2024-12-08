import { PrismaClient } from '@prisma/client';
import { hash } from 'bcrypt';
import { PASSWORD, USERS_DATA } from './constants/usersData';

const prisma = new PrismaClient();

const main = async () => {
  await prisma.users.createMany({
    data: await Promise.all(
      USERS_DATA.map(async (user) => ({
        ...user,
        password: await hash(PASSWORD, 10),
      })),
    ),
  });
};

main()
  .then(async () => {
    await prisma.$disconnect();
  })
  .catch(async (e) => {
    console.error(e);
    await prisma.$disconnect();
    process.exit(1);
  });
