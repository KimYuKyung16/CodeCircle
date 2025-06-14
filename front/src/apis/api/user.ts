import { authInstance } from '@apis/utils/instance';

interface UserResponse {
  userEmail: string;
  userId: number;
}

export const fetchUser = async (): Promise<UserResponse> => {
  const response = await authInstance.get(`/users/me`);
  return response.data;
};
