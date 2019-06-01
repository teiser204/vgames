from rest_framework import serializers
from games.models import (
    Feature, Platform, Language, Genre, Company, Game, Gallery
)
from django.dispatch import receiver
from django.db.models.signals import post_save
from django.contrib.auth.models import User

# --------------------------------------------------------------------
# Feature serializer
# --------------------------------------------------------------------
class FeatureSerializer(serializers.ModelSerializer):
    total_games = serializers.SerializerMethodField()

    class Meta:
        model = Feature
        fields = ('id', 'desc', 'total_games')

    def get_total_games(self, feature):
        return feature.games.count()


# --------------------------------------------------------------------
# Platform serializer
# --------------------------------------------------------------------
class PlatformSerializer(serializers.ModelSerializer):
    total_games = serializers.SerializerMethodField()

    class Meta:
        model = Platform
        fields = ('id', 'desc', 'total_games')

    def get_total_games(self, platform):
        return platform.games.count()


# --------------------------------------------------------------------
# Language serializer
# --------------------------------------------------------------------
class LanguageSerializer(serializers.ModelSerializer):
    total_games = serializers.SerializerMethodField()

    class Meta:
        model = Language
        fields = ('id', 'desc', 'total_games')

    def get_total_games(self, language):
        return language.games.count()


# --------------------------------------------------------------------
# Genre serializer
# --------------------------------------------------------------------
class GenreSerializer(serializers.ModelSerializer):
    total_games = serializers.SerializerMethodField()

    class Meta:
        model = Genre
        fields = ('id', 'desc', 'total_games')

    def get_total_games(self, genre):
        return genre.games.count()


# --------------------------------------------------------------------
# Company serializer
# --------------------------------------------------------------------
class CompanySerializer(serializers.ModelSerializer):
    total_games = serializers.SerializerMethodField()

    class Meta:
        model = Company
        fields = ('id', 'desc','total_games')

    def get_total_games(self, company):
        return company.games.count()


# --------------------------------------------------------------------
# Game gallery serializer
# --------------------------------------------------------------------
class GameGallerySerializer(serializers.ModelSerializer):
    
    class Meta:
        model = Gallery
        fields = ['game', 'image']


# --------------------------------------------------------------------
# Game List serializer
# --------------------------------------------------------------------
class GameListSerializer(serializers.ModelSerializer):
    company = CompanySerializer(read_only=True, many=False)
    owner = serializers.ReadOnlyField(source='user.username')

    class Meta:
        model = Game
        fields = (
            'id', 
            'title',
            'year',
            'rating',
            'price',
            'image',
            'company',
            'owner'
        )      


# --------------------------------------------------------------------
# Game Create serializer
# --------------------------------------------------------------------
class GameCreateSerializer(serializers.ModelSerializer):
    
    class Meta:
        model = Game
        fields = (
            'id', 
            'title',
            'desc',
            'year',
            'rating',
            'price',
            'url',
            'image',
            'company',
            'genres',
            'features',
            'platforms',
            'languages'
        )  


# --------------------------------------------------------------------
# Game Update serializer
# --------------------------------------------------------------------
class GameUpdateSerializer(serializers.ModelSerializer):
    
    class Meta:
        model = Game
        fields = (
            'id', 
            'title',
            'desc',
            'year',
            'rating',
            'price',
            'url',
            'image',
            'company',
            'genres',
            'features',
            'platforms',
            'languages',
            'gallery'

        )               

# --------------------------------------------------------------------
# Game details serializer
# --------------------------------------------------------------------
class GameDetailsSerializer(serializers.ModelSerializer):
    company = CompanySerializer(read_only=True, many=False)
    features = FeatureSerializer(read_only=True, many=True)
    genres = GenreSerializer(read_only=True, many=True)
    platforms = PlatformSerializer(read_only=True, many=True)
    languages = LanguageSerializer(read_only=True, many=True)
    gallery = GameGallerySerializer(read_only=True, many=True)
    owner = serializers.ReadOnlyField(source='user.username')

    class Meta:
        model = Game
        fields = (
            'id', 
            'title',
            'year',
            'rating',
            'price',
            'image',
            'url',
            'desc',
            'company',
            'features',
            'genres',
            'platforms',
            'languages', 
            'gallery',
            'owner'
        )      


# --------------------------------------------------------------------
# User serializer
# --------------------------------------------------------------------
class UserSerializer(serializers.ModelSerializer):
    games = GameListSerializer(read_only=True, many=True)

    class Meta:
        model = User
        fields = (
            'id',
            'username',
            'games'
        )