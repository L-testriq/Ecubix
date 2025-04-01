import { test, expect } from '@playwright/test';
import { loginPage } from '../pages/login'

test('test', async ({ page }) => {
  const login = new loginPage(page);
  login.goToURL()
  login.login("tomsmith","SuperSecretPassword!")
  await expect(page.getByRole('heading', { name: 'Welcome to the Secure Area.' })).toBeVisible();
  await page.getByRole('link', { name: 'Logout' }).click();
  await expect(page.getByRole('heading', { name: 'Login Page' })).toBeVisible();
});