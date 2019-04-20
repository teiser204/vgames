from django.shortcuts import get_object_or_404
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import generics

from .serializers import (
    FeatureSerializer, PlatformSerializer, LanguageSerializer, 
    GenreSerializer, CompanySerializer, GameListSerializer, 
    GameDetailsSerializer
)
from games.models import (
    Feature, Platform, Language, Genre, Company, Game
)
from games.api.filters import GameFilter



# --------------------------------------------------------------------
# Feature view
# --------------------------------------------------------------------
class FeatureList(generics.ListAPIView):
    queryset = Feature.objects.all()
    serializer_class = FeatureSerializer


# --------------------------------------------------------------------
# Platform view
# --------------------------------------------------------------------
class PlatformList(generics.ListAPIView):
    queryset = Platform.objects.all()
    serializer_class = PlatformSerializer    


# --------------------------------------------------------------------
# Language view
# --------------------------------------------------------------------
class LanguageList(generics.ListAPIView):
    queryset = Language.objects.all()
    serializer_class = LanguageSerializer    


# --------------------------------------------------------------------
# Genre view
# --------------------------------------------------------------------
class GenreList(generics.ListAPIView):
    queryset = Genre.objects.all()
    serializer_class = GenreSerializer

# --------------------------------------------------------------------
# Company view
# --------------------------------------------------------------------
class CompanyList(generics.ListAPIView):
    queryset = Company.objects.all()
    serializer_class = CompanySerializer

# --------------------------------------------------------------------
# Game list
# --------------------------------------------------------------------
class GameList(generics.ListAPIView):
    queryset = Game.objects.all()
    serializer_class = GameListSerializer
    filterset_class = GameFilter

    # if request.method == 'GET':
        
    #     # Έλεγχος για τα υποστηριζόμενα κριτήρια αναζήτησης
    #     games = Game.objects.all()
    #     ser = GameListSerializer(games, many=True, context={"request": request})
    #     return Response(ser.data)        

    # else:
    #     return Response(status=status.HTTP_400_BAD_REQUEST)


# --------------------------------------------------------------------
# Game details
# --------------------------------------------------------------------
@api_view(['GET'])
def game_details(request, game_id):
    game = get_object_or_404(Game, pk=game_id)
    ser = GameDetailsSerializer(game, many=False, context={"request": request})
    return Response(ser.data)      