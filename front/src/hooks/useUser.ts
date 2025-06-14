import { useQuery } from '@tanstack/react-query';
import { fetchUser } from '@apis/api/user';
import type { AxiosError } from 'axios';

export const useUser = () => {
  return useQuery({
    queryKey: ['user'],
    queryFn: fetchUser,
    staleTime: 1000 * 60 * 5,
    retry: (failureCount, error) => {
      if ((error as AxiosError).status === 401) return false;
      return failureCount < 2;
    },
  });
};
