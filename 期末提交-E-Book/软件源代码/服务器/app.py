from flask import Flask
from flask import request
import psycopg2

app = Flask(__name__)

conn = psycopg2.connect(database="Hzzone",
    host="127.0.0.1",
    port="5432",
    user="",
    password="")
cursor = conn.cursor()
# conn.select_db('ebook')


@app.route('/login', methods=['GET'])
def signin_form():
    return '''<form action="/login" method="post">
              <p><input name="user_account"></p>
              <p><input name="user_password" type="password"></p>
              <p><button type="submit">Sign In</button></p>
              </form>'''

@app.route('/login', methods=['POST'])
def login():
    user_account =  request.form['user_account']
    user_password = request.form['user_password']
    # operate the database
    current_user = cursor.execute("select * from user where current_user=%s"%user_account)
    print current_user
    if user_password==current_user[2]:
        return '<p>home</p>'
        # return 'login_success'
    # return 'login_failed'
    return "<p>h</p>"


@app.route('/register', methods=['POST'])
def register():
    user_account =  request.form['user_account']
    user_password = request.form['user_password']
    # operate the database
    if True:
        return 'register_success'
    return 'register_failed'

if __name__ == '__main__':
    app.run()