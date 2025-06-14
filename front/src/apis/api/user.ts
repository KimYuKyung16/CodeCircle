import { authInstance } from '@apis/utils/instance';

interface User {
  userEmail: string;
  userId: number;
}

interface UserResponse {
  success: boolean;
  message: string;
  data: User;
}

export const fetchUser = async (): Promise<UserResponse> => {
  const response = await authInstance.get(`/users/me`);
  return response.data;
};
