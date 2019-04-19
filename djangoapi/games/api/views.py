from django.shortcuts import get_object_or_404
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializers import (
    FeatureSerializer, PlatformSerializer, LanguageSerializer, 
    GenreSerializer, CompanySerializer, GameListSerializer, 
    GameDetailsSerializer
)
from games.models import (
    Feature, Platform, Language, Genre, Company, Game
)


# --------------------------------------------------------------------
# Feature view
# --------------------------------------------------------------------
@api_view(['GET'])
def feature_list(request):
    features = Feature.objects.all()
    ser = FeatureSerializer(features, many=True)
    return Response(ser.data)


# --------------------------------------------------------------------
# Platform view
# --------------------------------------------------------------------
@api_view(['GET'])
def platform_list(request):
    platforms = Platform.objects.all()
    ser = PlatformSerializer(platforms, many=True)
    return Response(ser.data)    


# --------------------------------------------------------------------
# Language view
# --------------------------------------------------------------------
@api_view(['GET'])
def language_list(request):
    languages = Language.objects.all()
    ser = LanguageSerializer(languages, many=True)
    return Response(ser.data)        


# --------------------------------------------------------------------
# Genre view
# --------------------------------------------------------------------
@api_view(['GET'])
def genre_list(request):
    genres = Genre.objects.all()
    ser = GenreSerializer(genres, many=True)
    return Response(ser.data)      


# --------------------------------------------------------------------
# Company view
# --------------------------------------------------------------------
@api_view(['GET'])
def company_list(request):
    companies = Company.objects.all()
    ser = CompanySerializer(companies, many=True)
    return Response(ser.data)      


# --------------------------------------------------------------------
# Game list
# --------------------------------------------------------------------
@api_view(['GET'])
def game_list(request):
    games = Game.objects.all()
    ser = GameListSerializer(games, many=True, context={"request": request})
    return Response(ser.data)        


# --------------------------------------------------------------------
# Game details
# --------------------------------------------------------------------
@api_view(['GET'])
def game_details(request, game_id):
    game = get_object_or_404(Game, pk=game_id)
    ser = GameDetailsSerializer(game, many=False, context={"request": request})
    return Response(ser.data)      