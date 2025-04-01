exports.loginPage = class loginPage {
    constructor(page) {
        this.page = page;
        this.username_field = page.getByRole('textbox', { name: 'Username' });
        this.password_field = page.getByRole('textbox', { name: 'Password' });
        this.login_btn = page.getByRole('button', { name: 'Login' })
    }

    async login(username, password) {
        await this.username_field.fill(username)
        await this.password_field.fill(password)
        await this.login_btn.click()
    }

    async goToURL(){
        await this.page.goto('https://the-internet.herokuapp.com/login');
    }
}