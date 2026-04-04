import sqlite3
from flask import Flask, request

app = Flask(__name__)

@app.route('/user')
def get_user():
    user_id = request.args.get('id')
    conn = sqlite3.connect('database.db')
    cursor = conn.cursor()
    
    # VULNERABILITY: SQL Injection via string formatting
    query = "SELECT * FROM users WHERE id = '%s'" % user_id
    cursor.execute(query)
    
    return str(cursor.fetchone())

if __name__ == '__main__':
    app.run()
