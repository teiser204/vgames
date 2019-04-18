from rest_framework import serializers
from games.models import (
    Feature, Platform, Language, Genre, Company)

# --------------------------------------------------------------------
# Feature serializer
# --------------------------------------------------------------------
class FeatureSerializer(serializers.ModelSerializer):
    total_games = serializers.ReadOnlyField()

    class Meta:
        model = Feature
        fields = ('id', 'desc', 'total_games')


# --------------------------------------------------------------------
# Platform serializer
# --------------------------------------------------------------------
class PlatformSerializer(serializers.ModelSerializer):
    total_games = serializers.ReadOnlyField()

    class Meta:
        model = Platform
        fields = ('id', 'desc', 'total_games')


# --------------------------------------------------------------------
# Language serializer
# --------------------------------------------------------------------
class LanguageSerializer(serializers.ModelSerializer):
    total_games = serializers.ReadOnlyField()

    class Meta:
        model = Language
        fields = ('id', 'desc', 'total_games')


# --------------------------------------------------------------------
# Genre serializer
# --------------------------------------------------------------------
class GenreSerializer(serializers.ModelSerializer):
    total_games = serializers.ReadOnlyField()

    class Meta:
        model = Genre
        fields = ('id', 'desc', 'total_games')


# --------------------------------------------------------------------
# Company serializer
# --------------------------------------------------------------------
class CompanySerializer(serializers.ModelSerializer):
    total_games = serializers.ReadOnlyField()

    class Meta:
        model = Company
        fields = ('id', 'desc', 'total_games')