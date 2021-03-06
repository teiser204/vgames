# Generated by Django 2.2 on 2019-04-20 08:26

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('games', '0007_auto_20190416_1411'),
    ]

    operations = [
        migrations.AlterField(
            model_name='game',
            name='price',
            field=models.DecimalField(decimal_places=2, max_digits=10, verbose_name='Τιμή'),
        ),
        migrations.AlterField(
            model_name='game',
            name='rating',
            field=models.DecimalField(decimal_places=2, max_digits=3, verbose_name='Βαθμολογία'),
        ),
    ]
