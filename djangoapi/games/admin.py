from django.contrib import admin
from .models import Game, Gallery, Feature, Platform, Language, Genre, Company

# Register your models here.
admin.site.register(Game)
admin.site.register(Gallery)
admin.site.register(Feature)
admin.site.register(Platform)
admin.site.register(Language)
admin.site.register(Genre)
admin.site.register(Company)

