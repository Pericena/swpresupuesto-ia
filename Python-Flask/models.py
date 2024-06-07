from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import Bcrypt
from flask_login import UserMixin, LoginManager

db = SQLAlchemy()
bcrypt = Bcrypt()
login_manager = LoginManager()

class User(UserMixin, db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(150), nullable=False, unique=True)
    email = db.Column(db.String(150), nullable=False, unique=True)
    password = db.Column(db.String(150), nullable=False)

class KPI(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    cuenta = db.Column(db.String(50), nullable=False)
    tipo = db.Column(db.String(50), nullable=False)  # Ingreso, Egreso
    categoria = db.Column(db.String(50), nullable=True)
    monto = db.Column(db.Float, nullable=False)
    fecha = db.Column(db.Date, nullable=False)
    user = db.relationship('User', backref=db.backref('kpis', lazy=True))

@login_manager.user_loader
def load_user(user_id):
    return User.query.get(int(user_id))
